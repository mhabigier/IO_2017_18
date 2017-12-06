package ch.makery.shop.model;

/**
 * Created by Wojtek on 20.03.2017.
 */
public enum ProductType {
    ACTION {
        @Override public String toString() {
            return "ACTION";
        }
    },
    HUMOUR {
        @Override public String toString() {
            return "HUMOUR";
        }
    },
    HORROR{
        @Override public String toString() {
            return "HORROR";
        }
    },
    FANTASY {
        @Override public String toString() {
            return "FANTASY";
        }
    },
    SCIENCE {
        @Override public String toString() {
            return "SCIENCE";
        }
    },
    NOVEL {
        @Override public String toString() {
            return "NOVEL";
        }
    },
    HISTORICAL {
        @Override public String toString() {
            return "HISTORICAL";
        }
    },
    NONE {
        @Override public String toString() {
            return "-";
        }
    },
}
