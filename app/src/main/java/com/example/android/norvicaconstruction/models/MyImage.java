package com.example.android.norvicaconstruction.models;

/**
 * Created by Android on 6/27/2017.
 */

public class MyImage {

    /**
     * This file is part of SqliteImage
     * <p/>
     * Created by GuoJunjun <junjunguo.com> on March 21, 2015.
     */

        private String title, description, path;

        private long datetimeLong;

        /**
         * Gets title.
         *
         * @return Value of title.
         */
        public String getTitle() { return title; }

        /**
         * Gets datetime.
         *
         * @return Value of datetime.
         */


        /**
         * Sets new datetimeLong.
         *
         * @param datetimeLong New value of datetimeLong.
         */

        /**
         * Sets new datetime.
         *
         * @param datetime New value of datetime.
         */


        /**
         * Gets description.
         *
         * @return Value of description.
         */
        public String getDescription() { return description; }

        /**
         * Sets new title.
         *
         * @param title New value of title.
         */
        public void setTitle(String title) { this.title = title; }

        /**
         * Gets datetimeLong.
         *
         * @return Value of datetimeLong.
         */
        public long getDatetimeLong() { return datetimeLong; }

        /**
         * Sets new description.
         *
         * @param description New value of description.
         */
        public void setDescription(String description) { this.description = description; }

        /**
         * Sets new path.
         *
         * @param path New value of path.
         */
        public void setPath(String path) { this.path = path; }

        /**
         * Gets path.
         *
         * @return Value of path.
         */
        public String getPath() { return path; }

        @Override public String toString() {
            return  "\nPath:" + path;
        }
    }
