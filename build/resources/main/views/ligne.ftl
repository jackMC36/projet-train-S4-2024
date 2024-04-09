<#import "utils.ftl" as u>

  <@u.page>
    <table>
      <tr>
        <th>Numéro</th>
        <th>Nom</th>
        <th></th>
      </tr>
       <#list trains as train>
         <tr>
           <td>${ligne.getNo()}</td>
           <td>${ligne.getNom()}</td>
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
