<#import "utils.ftl" as u>

<@u.page>
  <table>
    <tr>
      <th>Numéro de ligne</th>
      <th>Nom</th>
      <th></th>
      <th></th>
    </tr>
    <#list lignes as ligne>
      <tr>
        <td>${ligne.getNoLigne()}</td>
        <td>${ligne.getNom()}</td>
        <td>
          <form action="/ligne/supprimer?no=${ligne.getNoLigne()?c}" method="POST">
            <input type="submit" value="supprimer"/>
          </form>
        </td>
        <td>
          <form action="/arret/NoLigne=${ligne.getNoLigne()?c}" method="GET">
            <input type="submit" value="Voir les arrêts"/>
          </form>
        </td>
      </tr>
    </#list>
  </table>

  <p>
      <a href="/ligne/ajout">Ajouter</a>
  </p>    
</@u.page>
