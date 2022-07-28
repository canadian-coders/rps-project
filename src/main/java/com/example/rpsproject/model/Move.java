package com.example.rpsproject.model;

public enum Move {

    Rock {
        @Override
        public boolean winOrNot(Move move) {
            return (Scissors == move);
        }


    },

    Paper {
        @Override
        public boolean winOrNot(Move move) {
            return (Rock == move);
        }


    },

    Scissors {
        @Override
        public boolean winOrNot(Move move) {
            return (Paper == move);
        }


    };

    public abstract boolean winOrNot(Move move);

}