package Unit8;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Squall")
public class Squall extends Unit{
	public Squall() {
		super("Squall", "whatever");
	}

	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8915};
				break;
			case HP:
				switch(region) {
					case "GL":
						ids = new int[]{8916}; break;
					case "JP":
						ids = new int[]{8916, 11529}; break;
				}
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{4074}; break;
					case "JP":
						ids = new int[]{11517, 11529}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{4078}; break;
					case "JP":
						ids = new int[]{11521, 11529}; break;
				}
				break;
			case EX:
				switch(region) {
					case "GL":
						ids = new int[]{5386}; break;
					case "JP":
						ids = new int[]{5386, 11529}; break;
				}
				break;
			case AA:
				ids = new int[]{5390};
				break;
			case LD:
				switch(region) {
					case "GL":
						ids = new int[]{8914}; break;
					case "JP":
						ids = new int[]{8914, 11529}; break;
				}
				break;
			case BT:
				ids = new int[]{8529};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}

	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 5386: //EX
				ret.getAilmentById(1115).removeEffect(85);
				break;
			case 5390: //AA
				if(ret.getDetails().getHits().size() > 3) {
					ret.getDetails().getHits().clear();
					Ability.Details.Hit_Data hd = new Ability.Details.Hit_Data();
					hd.setArguments(new Integer[] {});
					hd.setEffect(new Ability.Details.Hit_Data.Effect(Ability.Details.Hit_Data.EffectType.E110, 1));
					ret.getDetails().getHits().add(hd);
					hd = new Ability.Details.Hit_Data();
					hd.setArguments(new Integer[] {1, 100, super.getBaseAbility(Ability.AttackName.S1).get(0).getId()});
					hd.setEffect(new Ability.Details.Hit_Data.Effect(Ability.Details.Hit_Data.EffectType.E136, 1));
					ret.getDetails().getHits().add(hd);
					hd = new Ability.Details.Hit_Data();
					hd.setArguments(new Integer[] {1, 100, super.getBaseAbility(Ability.AttackName.S2).get(0).getId()});
					hd.setEffect(new Ability.Details.Hit_Data.Effect(Ability.Details.Hit_Data.EffectType.E136, 1));
					ret.getDetails().getHits().add(hd);
				}
				break;
			case 8914: //LD
				if(ret.getAilmentById(1564).getRank() != -1) {
					ret.getAilmentById(1564).setRank(-1);
					ret.getAilmentById(1564).removeEffect(200, 205);
					ret.getAilmentById(1564).getEffects().stream().forEach(e -> {
						e.effectId = e.effectId == 1 ? 67 : e.effectId == 67 ? 1 : e.effectId;
					});
				}
				break;
			case 8529: //BT
				ret.getAilmentById(1641).getAuras().get(666).target = Ailment.Target.Party.getId();
				ret.getAilmentById(1641).getEffects().add(new Ailment.EffectGrouping(60));
				ret.getAilmentById(1641).removeEffect(60);
				ret.addStaticAilmentEffect(1641, "+50% party HP Damage dealt when dealing HP Splash damage");
				break;
			case 11529: //LD Boards Followup
				ret.addStaticHit("Triggers after attacking if ?**" + super.getSpecificAilment(1564).getName() + "**」 is active", 0);
				break;
		}
		return ret;
	}
}