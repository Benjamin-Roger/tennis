import type {Player} from "../../../domain/entity/Player";

export interface PlayerStat {
    label: string,
    data: string | number | undefined
}

export class PlayerDetailViewModel {
    id: number=0;
    title: string="Fiche du joueur";
    firstName: string="";
    lastName: string="";
    playerPicture?: string;
    countryName?:string;
    countryCode?: string;
    countryPicture?: string;
    stats?: PlayerStat[];

    static of(player:Player) {
        const viewModel = new PlayerDetailViewModel();
        viewModel.id = player.id;
        viewModel.title = `Fiche du joueur ${player.firstName} ${player.lastName}`;
        viewModel.firstName = player.firstName;
        viewModel.lastName = player.lastName;
        viewModel.playerPicture = player.picture;
        viewModel.countryName = player.country.name;
        viewModel.countryCode = player.country.code;
        viewModel.countryPicture = player.country.picture;
        viewModel.stats = [
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
            },
            {
                label: "Age",
                data: player.data?.age
            },
            {
                label: "Birthday",
                data: player.data?.birthday
            },
            {
                label: "Weight",
                data: player.data?.weight && player.data.weight/1000
            },
            {
                label: "Height",
                data: player.data?.height && player.data.height
            }
        ];

        return viewModel;
    }
}

export function usePlayerDetailViewModel(player: Player): PlayerDetailViewModel {
    return PlayerDetailViewModel.of(player);
}
