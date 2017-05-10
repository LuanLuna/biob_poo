package com.tcc.luan.biob.model.util;

import com.tcc.luan.biob.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luan on 04/09/16.
 */
public class Images {
    static Map<String, Integer> imageMap;

    static {
        imageMap = (new HashMap<String, Integer>());
        imageMap.put("cacto", R.drawable.cactus);
        imageMap.put("rosa", R.drawable.rose);
        imageMap.put("sunflower", R.drawable.sunflower);
        imageMap.put("galinha", R.drawable.chicken);
        imageMap.put("vaca", R.drawable.cow);
        imageMap.put("burro", R.drawable.donkey);
        imageMap.put("cachorro", R.drawable.dog);
    }

//    static int CACTUS = R.drawable.cactus;
//    static int chicken = R.drawable.chicken;
//    static int COW = R.drawable.cow;
//    static int DOG = R.drawable.dog;
//    static int DONKEY = R.drawable.donkey;
//    static int ROSE = R.drawable.rose;
//    static int SUNFLOWER = R.drawable.sunflower;
}
