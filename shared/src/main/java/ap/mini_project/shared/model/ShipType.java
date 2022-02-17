package ap.mini_project.shared.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum ShipType {
    BATTLESHIP {
        @Override
        public int getLength() {
            return 4;
        }

        @Override
        public int getWidth() {
            return 1;
        }
    }, CRUISER {
        @Override
        public int getLength() {
            return 3;
        }

        @Override
        public int getWidth() {
            return 1;
        }
    }, DESTROYER {
        @Override
        public int getLength() {
            return 2;
        }

        @Override
        public int getWidth() {
            return 1;
        }
    }, FRIGATE {
        @Override
        public int getLength() {
            return 1;
        }

        @Override
        public int getWidth() {
            return 1;
        }
    };

    public abstract int getLength();
    public abstract int getWidth();
}
