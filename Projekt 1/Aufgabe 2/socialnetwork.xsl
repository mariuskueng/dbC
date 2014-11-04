<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited by XMLSpy® -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <style>
      body {
        font: 100% normal "Helvetica Arial" sans-serif;
      }
      .person {
        margin-bottom: 80px;
      }
      .content {
        min-width: 500px;
      }
      .content, .profile-picture {
        float:left;
      }
      .clearfix {
        float: none;
        clear:both;
      }
    </style>
    <h2>My Social Network</h2>
    <h3>Profiles</h3>
    <xsl:for-each select="socialnetwork/person">
      <div class="person">
        <div class="content">
          <h3><xsl:value-of select="name"/></h3>
          <p>Gender: <xsl:value-of select="gender"/></p>
          <p>Age: <xsl:value-of select="age"/></p>
          <p>E-mail:
            <a>
              <xsl:attribute name="href">
                mailto:<xsl:value-of select="email"/>
              </xsl:attribute>
              <xsl:value-of select="email"/>
            </a>
          </p>
          <h3>Friends</h3>
          <ul>
            <xsl:for-each select="friends/person">
              <li>
                  <xsl:variable name="identifier" select="./@screenname" />
                  <xsl:value-of select="../../../person[@screenname=$identifier]/name" />
              </li>
            </xsl:for-each>
          </ul>
          <h3>Status messages</h3>
          <ul>
            <xsl:for-each select="statuses/status">
              <li>
                  <xsl:value-of select="."/>
              </li>
            </xsl:for-each>
          </ul>
        </div>
        <!--<xsl:value-of select="name/@attribute1" />-->
        <div class="profile-picture">
          <img width="300">
          <xsl:attribute name="src">
            <xsl:value-of select="@profilepic"/>
          </xsl:attribute>
          </img>
        </div>
        <div class="clearfix"></div>
      </div>
    </xsl:for-each>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
