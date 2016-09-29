package app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RestObject<T> {

  private boolean success;
  private T data;
  private String reason;

  public RestObject(boolean succeed, T data) {
    this.success = succeed; 
    if(succeed){
      this.data = data;
    }else{
      this.reason = String.valueOf(data);
    }
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  
}
