import * as functions from "firebase-functions";
import * as admin from "firebase-admin";
admin.initializeApp();

export const helloWorld = functions.firestore
  .document("/tournaments/{tournamentId}")
  .onCreate((snap, context) => addBotsToTournament(snap));

async function addBotsToTournament(snap: FirebaseFirestore.DocumentSnapshot) {
  const tournament = snap.data();
  if (!tournament) return;
  const bots = await getBots(tournament.bracketSize);


  tournament.rounds = [];
  tournament.rounds.push({
      contestants: []
  });

  tournament.rounds[0].contestants = tournament.rounds[0].contestants.concat(bots);

  await admin
    .firestore()
    .collection("/tournaments")
    .doc(snap.id)
    .set(tournament);
}

async function getBots(count: number) {
  const robots = await admin
    .firestore()
    .collection("/robots")
    .get();

  const tournamentRobots = [];

  const docs = robots.docs;

  while (tournamentRobots.length < count && docs.length > 0) {
    const i = Math.floor(docs.length * Math.random());
    const r = docs[i];
    tournamentRobots.push(r.data());
    docs.splice(i, 1);
  }

  return tournamentRobots;
}
