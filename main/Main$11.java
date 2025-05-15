package main;

/* loaded from: Main$11.class */
class Main$11 implements Runnable {
    final /* synthetic */ String[] val$args;

    Main$11(String[] strArr) {
        this.val$args = strArr;
    }

    @Override // java.lang.Runnable
    public void run() {
        new Main(this.val$args).setVisible(true);
    }
}
