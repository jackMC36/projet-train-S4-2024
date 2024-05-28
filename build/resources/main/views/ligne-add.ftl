<#import "utils.ftl" as u>

  <@u.page>
    <form action="/ligne" method="POST">
      <p>
        <label for="no">Numéro de Ligne</label>
        <input type="number" name="NoLigne" id="NoLigne" />
      </p>
      <p>
        <label for="type">Nom:</label>
        <input type="text" name="Nom" id="Nom" />
      </p>
      <p>
        <input type="submit" value="Ajouter"/>
      </p>
      </form>
  </@u.page>
