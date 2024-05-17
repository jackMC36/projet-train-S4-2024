<#import "utils.ftl" as u>

  <@u.page>
    <form action="/depart" method="POST">
      <p>
        <label for="no">Numéro de Ligne</label>
        <input type="number" name="no" id="noLigne" />
      </p>
      <p>
        <label for="type">Heure de départ</label>
        <input type="text" name="heure" id="heure" />
      </p>
      <p>
        <input type="submit" value="Ajouter"/>
      </p>
      </form>
  </@u.page>
