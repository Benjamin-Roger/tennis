export type playerCard = {
    name: string,
    rank: number,
    points: number,
    country: string,
};

const usePlayerCardViewModel = (data: playerCard) => ({
    name: data.name,
    stats:
        [
            {
                label: "Rank",
                data: `#${data.rank}`
            },
            {
                label: "Points",
                data: `#${data.points}`
            },
            {
                label: "Country",
                data: `#${data.country}`
            }
        ]
});

export default usePlayerCardViewModel;
