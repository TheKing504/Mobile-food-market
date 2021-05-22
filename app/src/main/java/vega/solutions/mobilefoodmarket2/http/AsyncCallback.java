package vega.solutions.mobilefoodmarket2.http;

public interface AsyncCallback<T> {
    void onCallback(T response);
    void onError(Throwable t);

}
