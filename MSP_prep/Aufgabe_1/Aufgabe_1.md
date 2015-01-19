# dbC MSP Lösung - Aufgabe 1

## a)

```xml
<?xml version="1.0" encoding="utf-8"?>
<sbb>
  <station>
    <name>Zürich, Schwamendingerplatz</name>
    <coordinates>
      <lat>47.4042</lat>
      <long>8.5739</long>
      <height>434</height>
    </coordinates>
    <transportmittel>T7</transportmittel>
    <transportmittel>T9</transportmittel>
  </station>
  <station>
    <name>Winterthur</name>
    <coordinates>
      <lat>47.5003</lat>
      <long>47.5003</long>
      <height>466</height>
    </coordinates>
    <transportmittel>S12</transportmittel>
    <transportmittel>B1</transportmittel>
    <transportmittel>B2</transportmittel>
  </station>
  <station>
    <name>Stettbach, Bahnhof</name>
    <coordinates>
      <lat>47.3972</lat>
      <long>8.5961</long>
      <height>437</height>
    </coordinates>
    <transportmittel>S12</transportmittel>
    <transportmittel>T7</transportmittel>
    <transportmittel>T12</transportmittel>
    <transportmittel>B760</transportmittel>
  </station>
  <schedule>
    <connection>
      <from>Zürich, Schwamendingerplatz</from>
      <to>Stettbach, Bahnhof</to>
      <departure>08:43</departure>
      <arrival>08:48</arrival>
      <transportmittel>T7</transportmittel>
    </connection>
    <connection>
      <from>Stettbach, Bahnhof</from>
      <to>Winterthur</to>
      <departure>08:56</departure>
      <arrival>09:09</arrival>
      <track_departure>2</track_departure>
      <track_arrival>5</track_arrival>
      <transportmittel>S12</transportmittel>
      </connection>
      </schedule>
      <transportmittel_detail>
      <transportmittel>
      <name>T7</name>
      <departure>Zürich, Wollishofen</departure>
      <arrival>Stettbach, Bahnhof</arrival>
      </transportmittel>
      <transportmittel>
      <name>S12</name>
      <departure>Brugg AG</departure>
      <arrival>Winterthur, Seen</arrival>
      </transportmittel>
      </transportmittel_detail>
      </sbb>

```

## b)

```xml
<xs:schema attributeFormDefault="unqualified" elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema">
  <xs:element name="sbb">
    <xs:complexType>
      <xs:sequence>
        <xs:element name="station" maxOccurs="unbounded" minOccurs="0">
          <xs:complexType>
            <xs:sequence>
              <xs:element type="xs:string" name="name"/>
              <xs:element name="coordinates">
                <xs:complexType>
                  <xs:sequence>
                    <xs:element type="xs:float" name="lat"/>
                    <xs:element type="xs:float" name="long"/>
                    <xs:element type="xs:short" name="height"/>
                  </xs:sequence>
                </xs:complexType>
              </xs:element>
              <xs:element type="xs:string" name="transportmittel" maxOccurs="unbounded" minOccurs="0"/>
            </xs:sequence>
          </xs:complexType>
        </xs:element>
      </xs:sequence>
    </xs:complexType>
  </xs:element>
</xs:schema>
```

## c)

### i.

```
sbb/station[transportmittel="T7"]/coordinates/height
```

### ii.

```
???
sbb/station[name="Winterthur"]/transportmittel/../../transportmittel_detail/transportmittel[name=text()]
```

### iii.

```
???
```

## d)

### 1)

```
station
```

### 2)

```
name
```

### 3)
```
<xsl:for-each select="coordinates">
  <xsl:value-of select="lat"/>,
  <xsl:value-of select="long"/>,
  <xsl:value-of select="height"/>
</xsl:for-each>
```

### 4)
```
<xsl:for-each select="transportmittel">
<xsl:value-of select="current()"/>,
</xsl:for-each>
```
