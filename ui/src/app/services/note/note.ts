import {Deserializable} from '../../commons/deserializable';

export class Note implements Deserializable {
  id: number;
  title: string;
  finished: boolean;
  created: Date;
  lastUpdated: Date;

  deserialize(input: any): this {
    Object.assign(this, input);
    this.created = new Date(input.created);

    if (input.lastUpdated) {
      this.lastUpdated = new Date(input.lastUpdated);
    }

    return this;
  }
}
