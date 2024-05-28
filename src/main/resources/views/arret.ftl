<#import "utils.ftl" as u>

  <@u.page>
    <table>
      <tr>
        <th>Numéro de Ligne</th>
        <th>Rang</th>
        <th>Ville</th>
        <th>Chrono</th>
      </tr>
       <#list arrets as arret>
         <tr>
           <td>${arret.getNoLigne()}</td>
           <td>${arret.getRang()}</td>
           <td>${arret.getVille()}</td>
           <td>${arret.getChrono()}</td>
           <td>
            <form action="/arret/supprimer?NoLigne=${arret.getNoLigne()?c}&Rang=${arret.getRang()?c}&Ville=${arret.getVille()}&Chrono=${arret.getChrono()?url}" method="POST">
                <input type="submit" value="supprimer"/>
          </td>
         </tr>
       </#list>
    </table>

    <p>
        <a href="/arret/ajout">Ajouter</a>
    </p>    
  </@u.page>
