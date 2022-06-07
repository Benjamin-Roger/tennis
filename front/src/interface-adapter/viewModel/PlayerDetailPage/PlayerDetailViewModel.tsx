import type {Player} from "../../../domain/entity/Player";

export interface PlayerStat {
    label: string,
    data: string | number | undefined
}

export class PlayerDetailViewModel {
    id: string="0";
    title: string="Fiche du joueur";
    firstName: string="";
    lastName: string="";
    fullName: string="";
    playerPicture?: string;
    countryName?:string;
    countryCode?: string;
    countryPicture?: string;
    stats?: PlayerStat[];

    static of(player:Player):PlayerDetailViewModel {
        const viewModel = new PlayerDetailViewModel();
        viewModel.id = player.id;
        viewModel.firstName = player.firstName;
        viewModel.lastName = player.lastName;
        viewModel.fullName = `${player.firstName} ${player.lastName}`;
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
                data: player.data?.birthday && new Date(player.data.birthday).toLocaleDateString(),
            },
            {
                label: "Weight",
                data: player.data?.weight && `${player.data.weight/1000} kg`
            },
            {
                label: "Height",
                data: player.data?.height && `${player.data.height} cm`
            }
        ];
        viewModel.title = `Fiche du joueur | ${viewModel.fullName}`;

        return viewModel;
    }
}

export function usePlayerDetailViewModel(player: Player): PlayerDetailViewModel {
    return PlayerDetailViewModel.of(player);
}
