package com.example.azs_fx_1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public enum TemplateAZS {
    ROAD {
        public ImageView getImageView() {
            return getImageViewByPath(getClass(), "/com/example/azs_fx_1/images/road.png");
        }

    },
    GRASS {
        public ImageView getImageView() {
            return getImageViewByPath(getClass(), "/com/example/azs_fx_1/images/grass.png");
        }
    },
    HIGHWAY {
        public ImageView getImageView() {
            return getImageViewByPath(getClass(), "/com/example/azs_fx_1/images/highway.png");
        }
    },
    CASHBOX {
        public ImageView getImageView() {
            return getImageViewByPath(getClass(), "/com/example/azs_fx_1/images/cashbox.png");
        }
    },
    ENTRY {
        public ImageView getImageView() {
            return getImageViewByPath(getClass(), "/com/example/azs_fx_1/images/entry.png");
        }
    },
    EXIT {
        public ImageView getImageView() {
            return getImageViewByPath(getClass(), "com/example/azs_fx_1/images/exit.png");
        }
    },
    FUEL_STATION {
        public ImageView getImageView() {
           return getImageViewByPath(getClass(), "com/example/azs_fx_1/images/fuelStation.png");
        }
    };
    public abstract ImageView getImageView();
    private static ImageView getImageViewByPath(Class context, String path) {
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(context.getResourceAsStream(path))));
        imageView.setFitHeight(30.0);
        imageView.setFitWidth(30.0);
        return imageView;
    }
}
