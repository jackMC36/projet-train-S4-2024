<#import "utils.ftl" as u>

  <@u.page>
    <form action="/arret" method="POST">
      <p>
        <label for="NoLigne">Numéro de NoLigne</label>
        <input type="number" name="NoLigne" id="NoLigne" />
      </p>
      <p>
        <label for="Rang">Numéro de rang</label>
        <input type="number" name="Rang" id="Rang" />
      </p>
      <p>
        <label for="Ville">Nom de la ville</label>
        <input type="text" name="Ville" id="Ville" />
      </p>
      <p>
        <label for="Chrono">Chrono (minutes)</label>
        <input type="text" name="Chrono" id="Chrono" />
      </p>
      <p>
        <input type="submit" value="Ajouter"/>
      </p>
      </form>
  </@u.page>
