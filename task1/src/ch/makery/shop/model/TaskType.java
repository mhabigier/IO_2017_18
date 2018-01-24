package ch.makery.shop.model;

/**
 * Created by Wojtek on 20.03.2017.
 */
public enum TaskType {
    DO_NOW {
        @Override public String toString() {
            return "IMPORTANT AND URGENT";
        }
    },
    PLAN_NEXT {
        @Override public String toString() {
            return "IMPORTANT, NOT URGENT";
        }
    },
    MANAGE{
        @Override public String toString() {
            return "NOT IMPORTANT, BUT URGENT";
        }
    },
    AVOID {
        @Override public String toString() {
            return "NOT IMPORTANT, NOT URGENT";
        }
    },
    NONE {
        @Override public String toString() {
            return "-";
        }
    },
}
