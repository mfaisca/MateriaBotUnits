package Unit7;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cait_Sith")
public class Cait_Sith extends Unit{
	public Cait_Sith() {
		super("Cait Sith", "cait", "caitsith", "keksith", "caitkek", "dreamy", "reeve");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5709};
				break;
			case HP:
				ids = new int[]{5711};
				break;
			case S1:
				ids = new int[]{5700, 5701};
				break;
			case S2:
				ids = new int[]{2539, 2540};
				break;
			case EX:
				ids = new int[]{5295};
				break;
			case AA:
				ids = new int[]{5726};
				break;
			case LD:
				ids = new int[]{};
				break;
			case BT:
				ids = new int[]{};
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
			case 5701:{
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled when **total party buffs** >= 6");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
				}
			case 5700:
				ret.getAilmentById(120).setRank(8);
				ret.fixMergeAbility(2562);
				break;
			case 2540:{
				ret.getAilmentById(55).setRank(2);
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled when **total party buffs** >= 6");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
				}
			case 2539:
				ret.getAilmentById(79).setRank(8);
				break;
			case 5295: //EX
				ret.getAilmentById(1184).getAuras().get(342).ailmentEffect = Ailment.EffectType.E4.getId();
				ret.getAilmentById(1184).getAuras().get(342).target = Ailment.Target.Party.getId();
				ret.getAilmentById(1184).getAuras().get(343).ailmentEffect = Ailment.EffectType.E8.getId();
				ret.getAilmentById(1184).getAuras().get(343).target = Ailment.Target.Party.getId();
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(adh);
				ret.getDetails().getHits().stream().filter(hd -> hd.getMaxBrvOverflow() == 150)
					.forEach(hd -> hd.setMaxBrvOverflow(100));
				adh = new Ability.Details.Hit_Data("BRV Potency: RNG% x 5 = 250% ~ 875% (150% overflow)");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(adh);
				adh = new Ability.Details.Hit_Data("Raises BRV Damage by 60% against ST");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
			break;
		}
		return ret;
	}
}