package Unit9;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.Details.Hit_Data;
import com.materiabot.GameElements.Ability.Details.Hit_Data.EffectType;
import com.materiabot.GameElements.Ability.Details.Hit_Data.Target;
import com.materiabot.GameElements.Ability.Details.Hit_Data.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Eiko")
public class Eiko extends Unit{
	public Eiko() {
		super("Eiko");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6187};
				break;
			case HP:
				ids = new int[]{6517};
				break;
			case S1:
				ids = new int[]{6184};
				break;
			case S2:
				ids = new int[]{6186};
				break;
			case EX:
				ids = new int[]{6190, 6191};
				break;
			case AA:
				ids = new int[]{6151};
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
			case 6184: //S1
				if(ret.getDetails().getHits().stream().noneMatch(h -> h.getType().equals(Type.HP))) {
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Magic, Type.HP, Target.ST);
					Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data();
					adh.setEffect(new Ability.Details.Hit_Data.Effect(EffectType.E100, Hit_Data.BasedOnStat.Stat14.getId()));
					adh.setArguments(new Integer[] {50, 20, 100100});
					adh.setTarget(Target.Party);
					ret.getDetails().getHits().add(adh);
				}break;
			case 6151: //AA
				ret.getAilmentById(1221).getAuras().get(375).ailmentEffect = Ailment.EffectType.E1.getId();
				ret.getAilmentById(1221).getAuras().get(375).target = Ailment.Target.Party.getId();
				break;
			case 6190: //EX
				if(ret.getAilmentById(1096).getEffects().stream().noneMatch(eg -> eg.fakeDesc != null))
					ret.getAilmentById(1096).getEffects().add(new Ailment.EffectGrouping("Enables 「**Double Holy**」"));
				break;
			case 6191: //EX HP++
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Dispels 「**Double White**」");
				if(!ret.getDetails().getHits().contains(adh)) {
					ret.getDetails().getHits().addAll(ret.getDetails().getHits());
					ret.getDetails().getHits().add(0, adh);
				}
				break;
		}
		return ret;
	}
}