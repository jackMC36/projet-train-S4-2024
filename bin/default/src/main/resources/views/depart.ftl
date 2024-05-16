<#import "utils.ftl" as u>

  <@u.page>
    <table>
      <tr>
        <th>Type</th>
        <th>Numéro</th>
        <th></th>
      </tr>
       <#list departs as depart>
         <tr>
           <td>${depart.getNoLigne()}</td>
           <td>${depart.getHeure()}</td>
           <td>${depart.getNoTrain()}</td>
           <td>
             <form action="/depart/supprimer?no=${train.getNoLigne()?c}" method="POST">
               <input type="submit" value="supprimer"/>
             </form>
           </td>
         </tr>
       </#list>
    </table>

    <p>
        <a href="/train/ajout">Ajouter</a>
    </p>    
  </@u.page>
