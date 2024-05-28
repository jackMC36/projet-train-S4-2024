<#import "utils.ftl" as u>

  <@u.page>
    <form action="/depart" method="POST">
      <p>
        <label for="no">Numéro de Ligne</label>
        <input type="number" name="NoLigne" id="NoLigne" />
      </p>
      <p>
        <label for="type">Numéro de Train</label>
        <input type="text" name="NoTrain" id="NoTrain" />
      </p>
      <p>
        <label for="type">Heure de départ</label>
        <input type="text" name="Heure" id="Heure" />
      </p>
      <p>
        <input type="submit" value="Ajouter"/>
      </p>
      </form>
  </@u.page>
