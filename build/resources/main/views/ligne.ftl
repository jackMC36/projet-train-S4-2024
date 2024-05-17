<#import "utils.ftl" as u>

  <@u.page>
    <table>
      <tr>
        <th>Type</th>
        <th>Numéro</th>
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
         </tr>
       </#list>
    </table>

    <p>
        <a href="/ligne/ajout">Ajouter</a>
    </p>    
  </@u.page>
