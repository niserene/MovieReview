package com.nishantdev961.moviereview

class AppExecutors {

    companion object{

        private lateinit var instance: AppExecutors

        fun getInstance(): AppExecutors{
            if(instance == null)
                instance = AppExecutors()
            return instance
        }
    }


}