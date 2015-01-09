package com.example.LocateMe;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    private final LatLng MOSCOW = new LatLng(55.7522200, 37.6155600);

    private HashMap<Marker, CustomMarker> markerHashMap;
    private ArrayList<CustomMarker> customMarkers;

    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        customMarkers = new ArrayList<CustomMarker>();

        setupMap();
    }

    private void setupMap() {
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        if (googleMap != null) {

            markerHashMap = new HashMap<Marker, CustomMarker>();

            customMarkers.add(new CustomMarker("Kremlin"
                    , "The Moscow Kremlin is a historic fortified complex at the heart of Moscow, " +
                    "overlooking the Moskva River to the south, Saint Basil's Cathedral and Red Square to the east, " +
                    "and the Alexander Garden to the west. " +
                    "It is the best known of kremlins (Russian citadels) and includes five palaces, four cathedrals, " +
                    "and the enclosing Kremlin Wall with Kremlin towers. " +
                    "The complex serves as the official residence of the President of the Russian Federation.", "kremlin"
                    , Double.parseDouble("55.751667")
                    , Double.parseDouble("37.617778")));
            customMarkers.add(new CustomMarker("MIEM", "Moscow Institute of Electronics and Mathematics (Technical University), MIEM — Russian higher educational institution in the field of electronics. Founded in 1962 as Moscow Institute of Electronic Machine Building. In 2011 joined with National Research University Higher School of Economics.\n" +
                    "\n" +
                    "Official Russian name — Московский государственный институт электроники и математики (технический университет), МИЭМ.", "miem", Double.parseDouble("55.755278"), Double.parseDouble("37.646389")));
            customMarkers.add(new CustomMarker("Gorky Park", "The Central Park of Rest and Culture Named After M. Gorky, to give it its full name, is one of the most famous places in Moscow (thanks presumably to Martin Cruz Smith's grizzly tale of a psychopathic professor, and the Hollywood film it inspired - shot mostly in Stockholm)." +
                    "\n" +
                    "The park stretches along the banks of the Moscow River, and is divided into two parts." +
                    "\n" +
                    "The other, older, half of the park is considerably more restrained, consisting of formal gardens and woodland that combine the former Golitsynskiy and Neskuchniy Gardens, names that crop up regularly in Russian literary classics. There are a number of fine old buildings dating from the late 18th and early 19th Centuries, including two summerhouses by the great Moscow architect Mikhail Kazakov (who designed the Senate Building in the Kremlin), and the first City Hospital.", "gorkypark", Double.parseDouble("55.731169"), Double.parseDouble("37.603197")));
            customMarkers.add(new CustomMarker("Poklonnaya Gora", "Poklonnaya Gora (Russian: Покло́нная гора́, literally \"bow-down hill\"; metaphorically \"Worshipful Submission Hill\"') is, at 171.5 metres, one of the highest spots in Moscow. Its two summits used to be separated by the Setun River, until one of the summits was razed in 1987. Since 1936, the area has been part of Moscow and now contains the Victory Park with many tanks and other vehicles used in the Second World War on display.\n" +
                    "\n" +
                    "Historically, the hill had great strategic importance, as it commanded the best view of the Russian capital. Its name is derived from the Russian for \"to bow down\", as everyone approaching the capital from the west was expected to do homage here. In 1812, it was the spot where Napoleon in vain expected the keys to the Kremlin to be brought to him by Russians.", "poklonnayagora", Double.parseDouble("55.7285"), Double.parseDouble("37.5")));

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MOSCOW, 15));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

            plotMarkers(customMarkers);

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    marker.showInfoWindow();

                    return false;
                }
            });

            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    CustomMarker customMarker = markerHashMap.get(marker);
                    intent.putExtra("title", customMarker.getTitle());
                    intent.putExtra("description", customMarker.getDescription());
                    startActivity(intent);
                }
            });
        }
    }

    private void plotMarkers(ArrayList<CustomMarker> customMarkers) {
        if (customMarkers.size() > 0) {
            for (final CustomMarker customMarker : customMarkers) {
                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(customMarker.getLat(), customMarker.getLng()));
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker));

                Marker currentMarker = googleMap.addMarker(markerOptions);
                markerHashMap.put(currentMarker, customMarker);
                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {
                        View view = getLayoutInflater().inflate(R.layout.custom_info, null);
                        CustomMarker customMarker1 = markerHashMap.get(marker);
                        TextView titleTextView = (TextView) view.findViewById(R.id.textView);
                        titleTextView.setText(customMarker1.getTitle());
                        TextView descriptionTextView = (TextView) view.findViewById(R.id.textView2);
                        descriptionTextView.setText(customMarker1.getDescription());
                        return view;
                    }
                });
            }
        }
    }

}


