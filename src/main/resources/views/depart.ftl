<#import "utils.ftl" as u>

  <@u.page>
    <table>
      <tr>
        <th>Numéro de Ligne</th>
        <th>Heure</th>
        <th>Numéro de Train</th>
      </tr>
       <#list departs as depart>
         <tr>
           <td>${depart.getNoLigne()}</td>
           <td>${depart.getHeure()}</td>
           <td>${depart.getNoTrain()}</td>
           <td>
            <form action="/depart/supprimer?NoLigne=${depart.getNoLigne()?c}&NoTrain=${depart.getNoTrain()?c}&Heure=${depart.getHeure()?url}" method="POST">
              <input type="submit" value="supprimer"/>
            </form>
           </td>
         </tr>
       </#list>
    </table>

    <p>
        <a href="/depart/ajout">Ajouter</a>
    </p>    
  </@u.page>
