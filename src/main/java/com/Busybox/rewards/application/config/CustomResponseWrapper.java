
  package com.Busybox.rewards.application.config;
  
  import java.io.CharArrayWriter; import java.io.PrintWriter;
  
  import jakarta.servlet.http.HttpServletResponse; import
  jakarta.servlet.http.HttpServletResponseWrapper;
  
  public class CustomResponseWrapper extends HttpServletResponseWrapper {
  
  private CharArrayWriter charArrayWriter = new CharArrayWriter(); private
  PrintWriter writer = new PrintWriter(charArrayWriter);
  
  public CustomResponseWrapper(HttpServletResponse response) { super(response);
  }
  
  @Override public PrintWriter getWriter() { return writer; }
  
  public String getCapturedResponseBody() { return charArrayWriter.toString();
  } }
 