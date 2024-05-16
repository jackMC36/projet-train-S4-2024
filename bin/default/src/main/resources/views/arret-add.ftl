<#import "utils.ftl" as u>

  <@u.page>
    <form action="/arret" method="POST">
      <p>
        <label for="no">Numéro de NoLigne</label>
        <input type="number" name="noLigne" id="noLigne" />
      </p>
      <p>
        <label for="noRang">Numéro de rang</label>
        <input type="number" name="noRang" id="noRang" />
      </p>
      <p>
        <label for="Ville">Nom de la ville</label>
        <input type="text" name="Ville" id="Ville" />
      </p>
      <p>
        <input type="submit" value="Ajouter"/>
      </p>
      </form>
  </@u.page>
