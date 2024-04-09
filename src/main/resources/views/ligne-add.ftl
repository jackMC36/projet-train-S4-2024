<#import "utils.ftl" as u>

  <@u.page>
    <form action="/ligne" method="POST">
      <p>
        <label for="no">Numéro de ligne</label>
        <input type="number" name="no" id="no" />
      </p>
      <p>
        <label for="type">Nom</label>
        <input type="text" name="nom" id="nom" />
      </p>
      <p>
        <input type="submit" value="Ajouter"/>
      </p>
      </form>
  </@u.page>
