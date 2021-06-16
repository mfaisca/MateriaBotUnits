package Unit12;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Vayne")
public class Vayne extends Unit{
	public Vayne() {
		super("Vayne");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{3956, 3957};
				break;
			case HP:
				ids = new int[]{3972, 3973};
				break;
			case S1:
				ids = new int[]{3948, 3949};
				break;
			case S2:
				ids = new int[]{3964, 3965};
				break;
			case EX:
				ids = new int[]{7033, 7034};
				break;
			case AA:
				ids = new int[]{7030};
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
		Ailment conqueror = this.getSpecificAilment(921);
		if(conqueror.getEffects().size() == 0) {
			conqueror.getAuras().put(123, new Ailment.Aura());
			conqueror.getAuras().remove(139);
			conqueror.getEffects().add(new Ailment.EffectGrouping(Ailment.EffectType.E60.getId()));
			conqueror.getAuras().get(123).ailmentEffect = Ailment.EffectType.E1.getId();
			conqueror.getAuras().get(123).rankData = new String[] {"-5", "-10", "-15"};
			conqueror.getAuras().get(123).target = Ailment.Target.AoE.getId();
			conqueror.setRank(-1);
			conqueror.setTarget(Ailment.Target.Self);
			conqueror.setArgs(new int[] {1});
		}
		switch(ret.getId()) {
			case 3965: //S2+
				ret.fixMissingAuraAilment(924, 140, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(924, 141, Ailment.EffectType.E5, Ailment.Target.Party);
			case 3957:
			case 3973:
			case 3949:
			case 7034:
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(conqueror.getId()).getName() + "**」 has 3 stacks", 0);
				break;
			case 3964: //S2
				ret.fixMissingAuraAilment(924, 140, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(924, 141, Ailment.EffectType.E5, Ailment.Target.Party);
			case 3956:
			case 3972:
				conqueror.setArgs(new int[] {1});
				if(!ret.getDetails().getAilments().contains(conqueror))
					ret.getDetails().getAilments().add(conqueror);
				break;
			case 3948:
				conqueror.setArgs(new int[] {2});
				if(!ret.getDetails().getAilments().contains(conqueror))
					ret.getDetails().getAilments().add(conqueror);
				break;
			case 7033:
				conqueror.setArgs(new int[] {3});
				if(!ret.getDetails().getAilments().contains(conqueror))
					ret.getDetails().getAilments().add(conqueror);
				break;
		}
		return ret;
	}
}