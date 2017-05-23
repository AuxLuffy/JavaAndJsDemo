package com.lenovo.sunzh.javaandjsdemo;

/**
 * Created by sunzh on 2017/5/18.
 */

class Single {
    private static final Single ourInstance = new Single();

    static Single getInstance() {
        return ourInstance;
    }

    private Single() {
    }
}
