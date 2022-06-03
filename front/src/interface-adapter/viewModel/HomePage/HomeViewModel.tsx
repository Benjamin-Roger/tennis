import type {Player} from "../../../domain/entity/Player";

export interface SinglePlayerSummary {
    id: string,
    name: string,
    picture?: string,
    stats: PlayerStat[]
}

export interface PlayerStat {
    label: string,
    data: string | number | undefined
}

export class PlayerSummariesViewModel {
    title: string = "Bienvenue dans l'annuaire du tennis";
    buttonLabel: string = "Rechercher un joueur";
    players: Array<SinglePlayerSummary> = new Array<SinglePlayerSummary>();
}

function usePlayerSummaryViewModel(player: Player): SinglePlayerSummary {

    return {
        id: player.id,
        name: `${player.firstName} ${player.lastName}`,
        picture: player.picture,
        stats:
            [
                {
                    label: "Rank",
                    data: player.data?.rank && `#${player.data.rank}`
                },
                {
                    label: "Points",
                    data: player.data?.points
                },
                {
                    label: "Country",
                    data: player.country.name
                }
            ]
    }
}

export default usePlayerSummaryViewModel;
