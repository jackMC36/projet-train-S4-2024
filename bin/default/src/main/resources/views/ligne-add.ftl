<#import "utils.ftl" as u>

  <@u.page>
    <form action="/ligne" method="POST">
      <p>
        <label for="no">Numéro de arret</label>
        <input type="number" name="no" id="no" />
      </p>
      <p>
        <label for="type">Type</label>
        <input type="text" name="type" id="type" />
      </p>
      <p>
        <input type="submit" value="Ajouter"/>
      </p>
      </form>
  </@u.page>
