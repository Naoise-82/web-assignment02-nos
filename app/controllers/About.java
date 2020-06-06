package controllers;

import play.*;
import play.mvc.*;

/**
 * Controller for the About page of the site
 */
public class About extends Controller
{
  public static void index() {
    Logger.info("Rendering about");
    render ("about.html");
  }
}
