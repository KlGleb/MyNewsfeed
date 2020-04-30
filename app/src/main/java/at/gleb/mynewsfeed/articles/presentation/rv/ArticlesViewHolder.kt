package at.gleb.mynewsfeed.articles.presentation.rv

import android.annotation.SuppressLint
import android.content.res.Resources
import android.text.format.DateUtils
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import at.gleb.mynewsfeed.databinding.ArticleItemBinding
import at.gleb.mynewsfeed.domain.entity.ArticleVo
import com.bumptech.glide.Glide
import java.text.ParseException
import java.util.*


class ArticlesViewHolder(private val binding: ArticleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val lp: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(250)
    )

    @SuppressLint("SetTextI18n")
    fun bindData(articleVo: ArticleVo?, clickListener: (ArticleVo) -> Unit) {
        articleVo?.let {
            binding.root.layoutParams = lp
            binding.root.setOnClickListener {
                clickListener.invoke(articleVo)
            }
            binding.titleText.text = articleVo.title
            binding.descrText.text = articleVo.description
            binding.dayText.text = articleVo.publishedDate.getTimeAgoString()

            Glide.with(binding.root.context)
                .load(articleVo.thumbnail)
                .centerCrop()
                .into(binding.imageView)

            binding.titleText.setBackgroundResource(0)
        } ?: run {
            binding.imageView.setImageResource(0)
            binding.root.setOnClickListener { }
            binding.titleText.text = "⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜"
            binding.descrText.text =
                "〰 〰〰〰〰〰〰〰〰 〰〰〰〰〰〰〰  〰〰〰〰 〰〰〰  〰〰〰〰〰〰〰   〰〰〰〰 〰〰〰  〰〰〰〰〰〰〰  〰〰〰〰〰〰〰  〰〰 〰〰〰〰〰 〰 〰〰〰〰〰〰〰〰 〰〰〰〰〰〰〰  〰〰〰〰 〰〰〰  〰〰〰〰〰〰〰   〰〰〰〰 〰〰〰  〰〰〰〰〰〰〰  〰〰〰〰〰〰〰  〰〰 〰〰〰〰〰"
            binding.dayText.text = "⬜ ⬜ ⬜"
        }

    }

    private fun Date.getTimeAgoString(): String {
        return try {
            val now = System.currentTimeMillis()
            DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
                .toString()
        } catch (e: ParseException) {
            e.printStackTrace()
            "undefined"
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}