/*
  Copyright 2015 Stefano Chizzolini. http://www.pdfclown.org

  Contributors:
    * Stefano Chizzolini (original code developer, http://www.stefanochizzolini.it)

  This file should be part of the source code distribution of "PDF Clown library"
  (the Program): see the accompanying README files for more info.

  This Program is free software; you can redistribute it and/or modify it under the terms
  of the GNU Lesser General Public License as published by the Free Software Foundation;
  either version 3 of the License, or (at your option) any later version.

  This Program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY,
  either expressed or implied; without even the implied warranty of MERCHANTABILITY or
  FITNESS FOR A PARTICULAR PURPOSE. See the License for more details.

  You should have received a copy of the GNU Lesser General Public License along with this
  Program (see README files); if not, go to the GNU website (http://www.gnu.org/licenses/).

  Redistribution and use, with or without modification, are permitted provided that such
  redistributions retain the above copyright notice, license and disclaimer, along with
  this list of conditions.
*/

package org.pdfclown.files;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.pdfclown.util.StringUtils;

/**
  File configuration.

  @author Stefano Chizzolini (http://www.stefanochizzolini.it)
  @since 0.1.2.1
  @version 0.1.2.1, 03/30/15
*/
public final class FileConfiguration
{
  // <dynamic>
  // <fields>
  private DecimalFormat realFormat;
  private XRefModeEnum xrefMode = XRefModeEnum.Plain;

  private final File file;
  // </fields>

  // <constructors>
  FileConfiguration(
    File file
    )
  {this.file = file;}
  // </constructors>

  // <interface>
  // <public>
  /**
    Gets the file associated with this configuration.
  */
  public File getFile(
    )
  {return file;}

  /**
    Gets the format applied to real number serialization.
  */
  public DecimalFormat getRealFormat(
    )
  {
    if(realFormat == null)
    {setRealFormat(5);}
    return realFormat;
  }
  
  /**
    Gets the document's cross-reference mode.
  */
  public XRefModeEnum getXRefMode(
    )
  {return xrefMode;}

  /**
    @see #getRealFormat()
  */
  public void setRealFormat(
    DecimalFormat value
    )
  {realFormat = value;}

  /**
    @param decimalPlacesCount
      Number of digits in decimal places.
    @see #getRealFormat()
  */
  public void setRealFormat(
    int decimalPlacesCount
    )
  {
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setDecimalSeparator('.');
    setRealFormat(new DecimalFormat("0." + StringUtils.repeat("#", decimalPlacesCount), symbols));
  }
  
  /**
    @see #getXrefMode()
  */
  public void setXRefMode(
    XRefModeEnum value
    )
  {file.getDocument().checkCompatibility(xrefMode = value);}

  /**
    @see #setRealFormat(DecimalFormat)
  */
  public FileConfiguration withRealFormat(
    DecimalFormat value
    )
  {
    setRealFormat(value);
    return this;
  }

  /**
    @see #setRealFormat(int)
  */
  public FileConfiguration withRealFormat(
    int decimalPlacesCount
    )
  {
    setRealFormat(decimalPlacesCount);
    return this;
  }

  /**
    @see #setXRefMode(XRefModeEnum)
  */
  public FileConfiguration withXRefMode(
    XRefModeEnum value
    )
  {
    setXRefMode(value);
    return this;
  }
  // </public>
  // </interface>
  // </dynamic>
}