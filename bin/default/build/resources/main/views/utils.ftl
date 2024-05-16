<#macro page>
<html>
  <head>
    <title>${title}</title>
    <link rel="stylesheet" href="style.css"/>
    <meta charset="utf-8" />
  </head>
  <body>
    <nav>
      <ul>
        <li><a href="/train">Trains</a></li>
        <li><a href="/arret">Arret</a></li>
        <li><a href="/depart">Depart</a></li>
        <li><a href="/ligne">Ligne</a></li>
      </ul>
    </nav>

    <h1>${title}</h1>

    <#-- ici est inséré le contenu du sous template  -->
         <#nested>
  </body>
</html>
</#macro>
