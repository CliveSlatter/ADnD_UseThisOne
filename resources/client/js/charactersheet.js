function pageLoad(){
    saveCharacter();
    generateAbilities();
}

function saveCharacter(){

}

function generateAbilities(){
    let abilities = ["STR","DEX","CON","INT","WIS","CON"]
    let abilitiesHTML;
    let diceRolls=[];
    let abilityBases=[];
    let abilityScore = 0;

        fetch('/ability/bonus', {method: 'get'}
        ).then(response => response.json()
        ).then(data => {
            if (data.hasOwnProperty('error')) {
                alert(data.error);
            } else if (data.hasOwnProperty('bonus')) {
                abilitiesHTML = `<table><tr><th>Ability Name</th><th>Ability Score</th><th>Ability Modifier</th><th>Temp Score</th><th>Temp Modifier</th></tr>`;
                for(let b of data.bonus){
                    abilitiesHTML +=    `<tr>`+
                        `<td class="col-dark">${b.ability}</td>`+
                        `<td class="col-light"><label>${b.base}</label></td>`+
                        `<td class="col-light"><label>${b.bonus}</label></td>`+
                        `<td class="col-light"><label></label>0</td>`+
                        `<td class="col-grey-border"><label>0</label></td>`+
                        `</tr>`;
                }
                abilitiesHTML+=`</table>`;
                document.getElementById("abilities").innerHTML = abilitiesHTML;
            }
        });
}