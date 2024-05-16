<#import "utils.ftl" as u>

  <@u.page>
    <table>
      <tr>
        <th>Type</th>
        <th>Numéro</th>
        <th></th>
      </tr>
       <#list trains as train>
         <tr>
           <td>${train.getNom()}</td>
           <td>${train.getNoLigne()}</td>
           <td>
             <form action="/ligne/supprimer?no=${train.getNo()?c}" method="POST">
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
