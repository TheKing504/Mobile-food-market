package vega.solutions.mobilefoodmarket2.http;

public interface APICallback<T> {
    void onCallback(T apiResponse);
    void onFailure(Throwable t);
}
