package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * Renders the start page for the site
 */
public class Start extends Controller
{
  public static void index() {
    Logger.info("Rendering Start");
    render ("start.html");
  }
}
