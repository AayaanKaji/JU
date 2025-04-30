<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="3.0">

  <xsl:output method="html" html-version="5.0" encoding="UTF-8" indent="no" doctype-system="about:legacy-compat"/>

  <xsl:template match="listing">
    <html>
      <head>
        <title>Web Technology Lab</title>
        <link rel="stylesheet" type="text/css" href="/assets/css/file_listing.css" />
      </head>
      <body>
        <h1>Assignments</h1>
        <div class="content">
          <table>
            <thead>
              <tr>
                <th>Filename</th>
                <th>Size</th>
                <th>Last Modified</th>
              </tr>
            </thead>
            <tbody>
              <xsl:apply-templates select="entries" />
            </tbody>
          </table>
        </div>
        <footer>
          ©2025 BE22100 | Powered by Apache Tomcat V9.0.102
        </footer>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="entries">
    <xsl:apply-templates select="entry">
      <xsl:sort select="@urlPath" order="ascending" />
    </xsl:apply-templates>
  </xsl:template>

  <xsl:template match="entry">
    <tr>
      <td>
        <xsl:variable name="urlPath" select="@urlPath"/>
        <a href="{$urlPath}">
          <pre>
            <xsl:apply-templates/>
          </pre>
        </a>
      </td>
      <td>
        <xsl:value-of select="@size"/>
      </td>
      <td>
        <xsl:value-of select="@date"/>
        <!-- <xsl:value-of select="format-dateTime(current-dateTime(), '[Y0001]-[M01]-[D01] [z]', 'en', (), 'Asia/Kolkata')"/> -->
      </td>
    </tr>
  </xsl:template>

</xsl:stylesheet>
