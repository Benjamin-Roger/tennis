import countriesJson from "../../data/countries.json";

export class CountryNameUtils {
    static countries = new Map<string, string>(JSON.parse(countriesJson));

    static find(code: string): string {
        return this.countries.get(code) || code;
    }
}
