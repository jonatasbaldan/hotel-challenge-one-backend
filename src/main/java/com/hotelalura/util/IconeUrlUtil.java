package com.hotelalura.util;

import javax.swing.*;
import java.net.URL;

public class IconeUrlUtil {

    public ImageIcon getIcone(String path) {
        URL url = getClass().getClassLoader().getResource(path);
        return new ImageIcon(url);
    }
}
