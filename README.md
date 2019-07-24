# Top Collègue API
Déploiement : https://top-collegue-api-gac.herokuapp.com

## Participer/Connecter
Connexion si présent dans la base, inscription sinon.
En cas de connexion, on peut quand même changer l'image.
utilisation : POST "/login" + { username, password, [pictureUrl] } -> ()

Fournit un cookie d'authentification en cas de connexion/inscription réussie.

## Deconnexion
Détruit le cookie d'authentification.
utilisation : POST "/logout" -> ()

## Infos collègue connecté
Renvoie des infos (pictureUrl, lastName, firstName, (score ?))
utilisation : GET "/me" -> {pictureUrl, lastName, firstName}

Nécessite un cookie d'authentification valide.

## Liste candidats
Renvoie la liste des collègues participants, et les votes de l'utilisateur courant.
utilisation : GET "/votes" -> \[ {id, lastName, firstName, pictureUrl, voteActuel | null} \]

Nécessite un cookie d'authentification valide.

## Voter
Ajoute/met à jour le vote de l'utilisateur pour un collegue donné.
utilisation : POST "/vote" + {idCandidat, vote:booleen} -> ()

Nécessite un cookie d'authentification valide.

## Classement
Renvoie la liste des participants, et leur score.
utilisation : GET "/classement" -> \[ {pictureUrl, lastName, firstName, score} \]

Nécessite un cookie d'authentification valide.
