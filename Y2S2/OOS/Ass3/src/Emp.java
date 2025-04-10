import java.io.*;
import java.util.*;

public class Emp{
  private int id;
  private double height;
  private String name;
  
  enum Name {
    Kirito, Alice, Bob;
  }
  
  @interface Inter1{}

  public Emp(int id, double height, String name){
    super();
    this.id = id;
    this.height = height;
    this.name = name;
  }

  Emp(){}
  
  public int geIid(){
    return id;
  }
  
  public void setId(int id, int num){
    this.id = id;
  }

  public double getHeight(){
    return height;
  }

  public String getName(){
    return name;
  }
  
  public void setName(String name){
    this.name = name;
  }

  class A{}
  class B{}
}
