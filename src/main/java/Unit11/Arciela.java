package Unit11;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Arciela")
public class Arciela extends Unit{
	public Arciela() {
		super("Arciela", "arci");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8085, 8091};
				break;
			case HP:
				ids = new int[]{8088, 8094};
				break;
			case S1:
				ids = new int[]{8231, 8085, 8088};
				break;
			case S2:
				ids = new int[]{8235, 8091, 8094};
				break;
			case EX:
				ids = new int[]{7751};
				break;
			case AA:
				ids = new int[]{8098};
				break;
			case LD:
				ids = new int[]{11123};
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
			case 8231: //S1
				Ability real1 = this.getSpecificAbility(7737);
				real1.setName(ret.getName());
				real1.addEffectHit(Ability.Details.Hit_Data.EffectType.E37, Ability.Details.Hit_Data.Target.Self, 0, 1477);
				real1.getDetails().getAilments().clear();
				real1.getDetails().getAilments().addAll(ret.getDetails().getAilments());
				ret = real1;
				ret.fixMissingAuraAilment(1476, 577, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1476, 578, Ailment.EffectType.E4, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1476, 579, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.getAilmentById(1476).getAuras().remove(552);
				if(ret.getDetails().getAilments().stream().noneMatch(a -> a.getId() == 1478)) {
					Ailment fc = new Ailment(this);
					fc.setId(1478);
					fc.setName("Fast Cast");
					fc.setTarget(Ailment.Target.Self);
					fc.getEffects().add(new Ailment.EffectGrouping(60));
					fc.getAuras().put(215, new Ailment.Aura());
					fc.setRate(100);
					fc.setDuration(3);
					fc.setArgs(new int[] {-1});
					ret.getDetails().getAilments().add(fc);
					Ailment.Aura a = fc.getAuras().get(215);
					a.id = 215;
					a.ailmentEffect = Ailment.EffectType.E215.getId();
					a.target = Ailment.Target.Party.getId();
					a.rankData = new String[] {"-1"};
				}
				//ret.fixMergeAbility(7737);
				break;
			case 8235: //S2
				Ability real2 = this.getSpecificAbility(7743);
				real2.setName(ret.getName());
				real2.addEffectHit(Ability.Details.Hit_Data.EffectType.E37, Ability.Details.Hit_Data.Target.Self, 0, 1476);
				real2.getDetails().getAilments().clear();
				real2.getDetails().getAilments().addAll(ret.getDetails().getAilments());
				ret = real2;
				ret.fixMissingAuraAilment(1477, 580, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1477, 581, Ailment.EffectType.E3, Ailment.Target.AoE);
				ret.getAilmentById(1477).getAuras().remove(553);
				ret.fixAddAilment(1554);
				ret.fixAddAilment(33);
				//ret.fixMergeAbility(7743);
				break;
			case 8091: //S2+BRV
			case 8094: //S2+HP
				//Missing Weakness damage to debuffed enemies
				break;
			case 8098: //LD
				ret.fixMissingAuraAilment(1533, 591, Ailment.EffectType.E1, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}