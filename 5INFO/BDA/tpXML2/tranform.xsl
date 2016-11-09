<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/carnetDAdresse">
  <html>
    <head>
      <meta charset="UTF-8"></meta>
      <meta name="author" content="Aurelien Fontaine"></meta>
      <meta name="description" content="Test page for XML lab"></meta>
      <title>Carnet d'adresses</title>
    </head>
    <body>
      <h1>Carnet d'adresses</h1>
      <table>
        <thead>
          <tr>
            <td>Nom</td>
            <td>Prenom</td>
            <td>Organisme</td>
          </tr>
        </thead>
        <tbody>
          <xsl:for-each select="carteDeVisite">
            <tr>
              <td><xsl:value-of select="nom"/></td>
              <td><xsl:value-of select="prenom"/></td>
              <td><xsl:value-of select="organisme"/></td>
            </tr>
          </xsl:for-each>
        </tbody>
      </table>
      <p>Nombre de personnes : <xsl:value-of select="count(carteDeVisite)"/></p>
      <p>Nombre de villes différentes : <xsl:value-of select="count(//ville[not(. = ../following-sibling:://ville)])"/></p>
    </body>
  </html>
</xsl:template>

</xsl:stylesheet>
