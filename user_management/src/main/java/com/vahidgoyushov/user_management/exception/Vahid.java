package com.vahidgoyushov.user_management.exception;

public class Vahid {
      private static Object password;

      public static void validate(String password)
              throws VahidException {
          if (password.length() < 8 || password.isEmpty()) {
              throw new VahidException("Password bos buraxila bilmez ve minimum 8 simvoldan ibaret olmalidir!");
          }
          System.out.println("Xeta bas verdi:" + password);
      }

      public static void main(String[] args) {
          try {
              validate((String) password);
          } catch (VahidException e) {
              System.out.println("Xeta bas verdi:"+e.getMessage());
          }

      }
  }
